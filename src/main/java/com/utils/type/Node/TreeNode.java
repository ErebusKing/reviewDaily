package com.utils.type.Node;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈LawEntry转 树状〉
 *  node包含子node
 *
 * 抽象化LawEntry得到树状关系
 * @param id,parentId,name 对应标准的TreeNode对象
 * 根节点时默认处理parentId为0
 * id表示层级， name表示该层的 值
 * @author Administrator
 * @create 2019/4/20 0020 8:26
 * @since 1.0.0
 * History:
 * wangb       20190420      1.0        树状结构原型，但是去重的时候有难度（所有层级不能重复）
 * 作者姓名      修改时间       版本号           描述
 */
public class TreeNode {
    private String id;
    private String name;
    private String parentId;
    private String parentName;
    private List<TreeNode> children;

    public TreeNode(String id, String name, String parentId, String parentName) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public TreeNode(String id, String name, String parentId, String parentName, List<TreeNode> children) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}