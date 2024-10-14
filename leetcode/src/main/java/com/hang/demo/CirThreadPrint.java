package com.hang.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: CALYEE
 * @CreateTime: 2024-10-09
 * @Description: 多线程轮流打印ABC
 * @Version: 1.0
 */
public class CirThreadPrint {
    private char[] charsToPrint;
    private int currentCharIndex = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public CirThreadPrint(String chars) {
        this.charsToPrint = chars.toCharArray();
    }

    public void print() {
        for (int i = 0; i < charsToPrint.length; i++) {
            lock.lock(); // 获取锁
            try {
                // 当前线程应该打印的字符索引是(i)，循环直到当前索引等于currentCharIndex % charsToPrint.length
                while (currentCharIndex % charsToPrint.length != i) {
                    condition.await(); // 如果不是当前线程的轮次，则等待
                }
                // 当前线程的轮次，打印对应的字符
                System.out.print(charsToPrint[i]);
                // 更新currentCharIndex，以便下一个字符可以被打印
                currentCharIndex++;
                // 唤醒所有等待的线程，以便它们可以检查是否轮到它们执行
                condition.signalAll();
            } catch (InterruptedException e) {
                // 如果线程在等待过程中被中断，打印堆栈跟踪信息
                System.out.println("[e Msg]: " + e.getMessage());
            } finally {
                // 在try块之后执行，确保锁被释放
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        String charsToPrint = "123";
        CirThreadPrint alternatePrint = new CirThreadPrint(charsToPrint);
        for (int i = 0; i < charsToPrint.length(); i++) {
            new Thread(() -> alternatePrint.print()).start();
        }
    }
}
