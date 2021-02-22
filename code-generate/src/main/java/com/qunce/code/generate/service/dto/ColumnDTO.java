package com.qunce.code.generate.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ColumnDTO implements Serializable {

    private static final long serialVersionUID = 529853691627229601L;

    private String columnName;

    private String columnType;

    private Long columnLength;

    private Long columnPrecision;

    private Long columnScale;

    private String columnComment;
    private String columnConstraint;

}
