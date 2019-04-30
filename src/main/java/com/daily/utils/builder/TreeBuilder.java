package com.daily.utils.builder;

import com.daily.utils.Node.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈TreeNode建树〉
 *
 * @author Administrator
 * @create 2019/4/20 0020 8:27
 * @since 1.0.0
 * History:
 * <author>     <time>      <version>       <desc>
 * 作者姓名      修改时间       版本号           描述
 */
public class TreeBuilder {


    /**
     * 两层循环建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes)
    {
        List<TreeNode> trees = new ArrayList();
        for (TreeNode node : treeNodes)
        {
            if ("0".equalsIgnoreCase(node.getParentId()))
            {
                trees.add(node);
            }

            for (TreeNode it : treeNodes)
            {
                if (it.getParentId() == node.getId())
                    if (node.getChildren() == null)
                        node.setChildren(new ArrayList<TreeNode>());
                    node.getChildren().add(it);
            }
        }

        return trees;
    }


    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
   public List<TreeNode> buildByRecursive(List<TreeNode> treeNodes)
   {
       List<TreeNode> trees = new ArrayList<>();
       for (TreeNode node : treeNodes)
       {
           if ("0".equalsIgnoreCase(node.getParentId()))
           {
               trees.add(findChildren(node,treeNodes));
           }
       }
       return trees;
   }

    /**
     * 递归查找子节点
     * @param node
     * @param treeNodes
     * @return
     */
    public TreeNode findChildren(TreeNode node,List<TreeNode> treeNodes)
    {
        for (TreeNode it : treeNodes)
        {
            if(node.getId().equals(it.getParentId()))
            {
                if (node.getChildren() == null)
                    node.setChildren(new ArrayList<TreeNode>());
                node.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return node;
    }

    /**
     *  去重
     * TODO  N层数据怎么处理，怎样才最简单
     * 这里只过滤了 当前层 = 1层
     * 如果多层结构中出现 不同分支下的 2层一致，这里就不能区别
     * 结论：
     * 只要同层级，不允许出现（2个以及以上）的相同name,不允许倒数第二级重复
     */
    public static List removeDuplicate(List<TreeNode> nodes) {

        List<TreeNode> unique = nodes.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.
                                comparing(TreeNode::getName).thenComparing(TreeNode::getParentId)
                                .thenComparing(TreeNode::getId).thenComparing(TreeNode::getParentName))),
                        ArrayList::new)
        );
//        unique.forEach(p -> System.out.println(p));
        return unique;
    }
}
