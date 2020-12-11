package com.qunce.grammar;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class ResetAttributes {

    private String name;

    private List<String> addresses;

    public static void main(String[] args) {
        ResetAttributes resetAttributes = new ResetAttributes();
        resetAttributes.name = "goodman";
        resetAttributes.addresses = Arrays.asList("随州", "武汉");
        resetAttributes.reset();

        System.out.println(resetAttributes.name);
        System.out.println(resetAttributes.addresses);
    }

    private void reset() {
        resetObject(this.name);
        resetList(this.addresses);
    }

    private void resetObject(Object obj) {
        if (Objects.nonNull(this.name)) {
            this.name = null;
        }
    }

    private void resetList(List list) {
        if (CollectionUtils.isNotEmpty(this.addresses)) {
            this.addresses = new ArrayList();
        }
    }


}
