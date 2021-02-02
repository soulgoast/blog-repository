package com.qunce.algorith.list_to_tree;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class PerformanceConverter {

    public List<Node> listToTree(List<Node> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return nodes;
        }
        Map<Long, Node> IdAndNodeMap = nodes.stream().collect(Collectors.toMap(Node::getId, item -> item));

        List<Node> treeNodes = new ArrayList<>();
        for (Node node : nodes) {
            Long parentId = node.getParentId();
            Node parentNode = IdAndNodeMap.get(parentId);

            if (Objects.isNull(parentId)) {
                treeNodes.add(node);
            } else {

                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<>());
                }
                parentNode.getChildren().add(node);
            }
        }
        return treeNodes;
    }

    @Test
    public void test() {
        List<Node> list = Arrays.asList(
                new Node(1L, "1", null, null)
        , new Node(2L, "2", 1L, null)
        , new Node(3L, "3", 1L, null));
        System.out.println(listToTree(list));
    }

}
