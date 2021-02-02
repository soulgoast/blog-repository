package com.qunce.algorith.list_to_tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {

    private Long id;

    private String name;

    private Long parentId;

    private List<Node> children;

}
