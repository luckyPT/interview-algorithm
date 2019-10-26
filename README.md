# interview-algorithm
- 大数相减

- 数组中仅有一个数字出现过一次，其余均出现过2次；找到这个数字；

- 数组中仅有两个数字出现过一次，其余均出现过2次；找到这两个数字；

- 有序奇数个元素的数列随机旋转之后，找到中间数

- 判断链表是否有环

- 求链表长度（考虑有环和无环的情况，空间复杂度O(1)）

- 两个链表是否相交（分两个无环链表是否相交 & 两个有环链表是否相交，一个有环一个无环不可能相交）

- 链表按K分组，组内翻转

- 单链表选择排序

- [链表增删、反转](https://github.com/luckyPT/luckyPT/blob/master/docs/interview/list.md)

- [排序与查找算法](https://github.com/luckyPT/luckyPT/blob/master/docs/interview/dataStructureAndAlgorithms.md)

- [树的遍历](https://github.com/luckyPT/luckyPT/blob/master/docs/interview/dataStructureAndAlgorithms.md)

- [翻转二叉树](https://github.com/luckyPT/luckyPT/blob/master/docs/interview/tree.md)

- 二叉查找树转有序双向链表

- 树的边界逆时针遍历

- 从一个树中，找到最大子树（结点数最多），并且该子树是二叉搜索树

- zigZag打印二叉树

- 一个二叉树中是否包含另一个二叉树的拓扑结构

- 树中两个结点的最近根节点

- 优雅的打印二叉树

- 找到数组最长子串，满足和等于某个值

- 字符串分割，要求分割后每个字符仅出现在一个子串，并且得到的子串数量最大的分割方式。

- 顺时针输出矩阵

- 有序矩阵中的查找某个值(二维搜索)

- 根据先序遍历和中序遍历，构建二叉树

- 字符串中最长不重复子串

- 数组和最大的子串

- 找到最长递增序列(不一定连续)

- 乱序数列找出最长连续数字的个数

- 最长公共子序列

- 最长公共子串？？

- 计算x^y

- 返回数组中两数之和等于某值的元素索引（可扩展到三个元素，输出的并非索引 而是元素值，需要去重）

- 验证回文字符串，（除数字和字符外，其他字符不计入）

- 求大小为n的数组的众数

- 分割回文串

- 字符串单词拆分(是否可以拆分，进阶 找到所有拆分的情况)

- 寻找字符串中第一次出现且不重复的字符(假定只包含小写字母)

- 乘积最大的子序列
- 从1开始，打印至最大的n位数
- [选择/快速/归并排序](src/main/java/com/pt/leetcode/sort/ArraySort.java)
- [基于数组实现栈](src/main/java/com/pt/leetcode/stack/MyArrayStack.java)
- [基于链表实现栈](src/main/java/com/pt/leetcode/stack/MyLinkStack.java)

### leetCode
[4. 寻找两个有序数组的中位数](src/main/java/com/pt/leetcode/bin/search/LoopOrderedArraySearch.java)<br>
[5. 最长回文子串](src/main/java/com/pt/leetcode/string/MaxCommonPalindromeStr.java)
[10. 正则表达式匹配](src/main/java/com/pt/leetcode/string/RegexMatch.java)
[15. 三数之和](src/main/java/com/pt/leetcode/ThreeSum.java)<br>
[21. 合并两个有序链表](src/main/java/com/pt/leetcode/list/MergeTwoList.java)<br>
[30. 串联所有单词的子串](src/main/java/com/pt/leetcode/string/StrConcatenate.java)
[32. 最长有效括号](src/main/java/com/pt/leetcode/stack/ValidParentheses.java)
[33. 搜索旋转排序数组](src/main/java/com/pt/leetcode/bin/search/LoopOrderedArraySearch.java)<br>
[41. 缺失的第一个正数](src/main/java/com/pt/leetcode/MissPositive.java)<br>
[46. 全排列](src/main/java/com/pt/leetcode/recursion/AllArrange.java)<br>
[69. x 的平方根](src/main/java/com/pt/leetcode/bin/search/Sqrt.java)<br>
[104. 二叉树的最大深度](src/main/java/com/pt/leetcode/tree/MaxDepth.java)
[118. 杨辉三角](src/main/java/com/pt/leetcode/recursion/YangHuiSanJiao.java)<br>
[119. 杨辉三角 II](src/main/java/com/pt/leetcode/recursion/YangHuiSanJiao.java)<br>
[142. 环形链表 II](src/main/java/com/pt/leetcode/list/DetectCycle.java)<br>
[144. 二叉树的前序遍历](src/main/java/com/pt/leetcode/tree/Traversition.java)
[145. 二叉树的后序遍历](src/main/java/com/pt/leetcode/tree/Traversition.java)
[146. LRU缓存机制](src/main/java/com/pt/leetcode/list/LRUCache.java)<br>
[148. 排序链表](src/main/java/com/pt/leetcode/list/ListSort.java)<br>
[171. Excel表列序号](src/main/java/com/pt/leetcode/ExcelColNum2ColTitle.java)<br>
[206. 反转链表(递归实现)](src/main/java/com/pt/leetcode/recursion/ListReverse.java)<br>
[215. 数组中的第K个最大元素](src/main/java/com/pt/leetcode/sort/KthLargest.java)<br>
[227. 基本计算器 II](src/main/java/com/pt/leetcode/stack/CalculateStrValue.java)
[234. 回文链表](src/main/java/com/pt/leetcode/list/PalindromeValidate.java)<br>
[239. 滑动窗口最大值](src/main/java/com/pt/leetcode/queue/WindowMaxValues.java)<br>
[622. 设计循环队列（链表实现）](src/main/java/com/pt/leetcode/queue/MyLinkCiycularQueue.java)<br>
[622. 设计循环队列（数组实现，待完成）](src/main/java/com/pt/leetcode/queue/MyArrayCircularQueue.java)<br>
[912. 排序数组(归并排序递归实现)](src/main/java/com/pt/leetcode/recursion/MergeSort.java)<br>
[912. 排序数组(快速排序递归实现)](src/main/java/com/pt/leetcode/recursion/QuickSort.java)<br>
[1106. 解析布尔表达式](src/main/java/com/pt/leetcode/stack/ParseBoolExpr.java)