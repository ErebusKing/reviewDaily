package com.utils.type.builder;

import com.daily.domain.SysLawEntry;
import com.utils.type.Node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈适应LawEntry的树状结构要求〉
 *
 * @author Administrator
 * @create 2019/4/20 0020 9:56
 * @since 1.0.0
 * History:
 * <author>     <time>      <version>       <desc>
 * 作者姓名      修改时间       版本号           描述
 */
public class LawEntryTreeBuilder extends TreeBuilder {


    /**
     * 配合实际需求，除了id(层级) 还包括 name(内容)
     * @param node
     * @param treeNodes
     * @return
     */
    @Override
    public TreeNode findChildren(TreeNode node, List<TreeNode> treeNodes)
    {
        for (TreeNode it : treeNodes) {
            if (node.getId().equals(it.getParentId()) && node.getName().equals(it.getParentName())){
                if (node.getChildren() == null)
                    node.setChildren(new ArrayList<TreeNode>());
                node.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return node;
    }



    /**
     * 一条lawEntry对象转成 多条树结构数据 Node
     * @param lawEntry
     * @return
     */
    public static List<TreeNode> transLawEntry2Nodes(SysLawEntry lawEntry)
    {
        TreeNode node = null;
        TreeNode secChild = null;
        TreeNode thrChild = null;
        TreeNode fthChild = null;
        List<TreeNode> nodes = new ArrayList<>();

        if (lawEntry != null)
        {
            node = new TreeNode("1",lawEntry.getDiscipline(),"0","0");
            secChild = new TreeNode("2",lawEntry.getChapter(),"1",lawEntry.getDiscipline());
            thrChild = new TreeNode("3",lawEntry.getAspect(),"2",lawEntry.getChapter());
            String supplement = "".equalsIgnoreCase(lawEntry.getSupplement())?"":"(" +lawEntry.getSupplement()+")";
            fthChild = new TreeNode("4",lawEntry.getContent()+supplement,"3",lawEntry.getAspect());

            nodes.add(node);
            nodes.add(secChild);
            nodes.add(thrChild);
            nodes.add(fthChild);
        }

        return nodes;
    }


    /**
     * 得到的所有lawEntry统合
     * @param lawEntries
     * @return
     */
    public List<TreeNode> entry2ContentNode(List<SysLawEntry> lawEntries)
    {
        List<TreeNode> nodes = new ArrayList<>();
        if(lawEntries != null && lawEntries.size() != 0)
        {
            for (SysLawEntry entry : lawEntries)
            {
                nodes.addAll(transLawEntry2Nodes(entry));
            }

            //去重后递归关联
            nodes = buildByRecursive(removeDuplicate(nodes));
        }

        return nodes;
    }



}
