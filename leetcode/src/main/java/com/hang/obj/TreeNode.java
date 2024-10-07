package com.hang.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: Study
 * @package: com.hang.obj
 * @className: TreeNode
 * @author: Calyee
 * @description: 二叉树
 * @date: 2024/09/29 周日 15:53
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
}
