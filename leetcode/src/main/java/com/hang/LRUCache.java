package com.hang;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: CALYEE
 * @CreateTime: 2024-09-29
 * @Description: 最少使用的缓存
 * @Version: 1.0
 */
public class LRUCache {

    private Entry head, tail;
    private int capacity;
    private int size;
    private Map<Integer, Entry> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity + 2);
        head = new Entry();
        tail = new Entry();
        this.size = 0;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Entry entry = cache.get(key);
        if (entry != null) {
            // 需要把当前节点移动到顶部
            moveToHead(entry);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Entry entry = cache.get(key);
        if (entry != null) {
            entry.value = value;
            moveToHead(entry);
            return;
        }
        // 如果容量满了，删除尾节点
        if (size == capacity) {
            Entry del = tail.pre;
            delCurrentNode(del);
            cache.remove(del.key);
            size--;
        }
        Entry e = new Entry(key, value);
        cache.put(key, e);
        moveToHead(e);
        size++;
    }

    /**
     * 把当前节点置顶
     *
     * @param entry 当前节点
     */
    private void moveToHead(Entry entry) {
        // 1. 移除旧关系：先移除node2，然后连接node3和node1
        delCurrentNode(entry);
        // 2. 然后处理移动头结点关系
        head.next.pre = entry;       // head结点的后面的节点的前指针指向当前节点
        entry.next = head.next;      // 当前节点连接head结点的下一个节点（原始节点）
        entry.pre = head;            // 当前节点的前指针为head
        head.next = entry;           // head节点的下一个指针为当前节点
    }

    /**
     * 删除当前节点
     *
     * @param entry 当前节点
     */
    private void delCurrentNode(Entry entry) {
        // 1. 移除旧关系：先移除node2，然后连接node3和node1
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
    }

    /**
     * 双向链表
     */
    class Entry {
        int key;
        int value;
        Entry next;
        Entry pre;

        public Entry() {
        }

        public Entry(int _key, int _value) {
            this.key = _key;
            this.value = _value;
        }
    }
}

