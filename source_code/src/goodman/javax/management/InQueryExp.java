/*
 * Copyright (c) 1999, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package goodman.javax.management;


import goodman.javax.management.*;
import goodman.javax.management.QueryEval;
import goodman.javax.management.QueryExp;
import goodman.javax.management.ValueExp;

/**
 * This class is used by the query-building mechanism to represent binary
 * operations.
 * @serial include
 *
 * @since 1.5
 */
class InQueryExp extends QueryEval implements QueryExp {

    /* Serial version */
    private static final long serialVersionUID = -5801329450358952434L;

    /**
     * @serial The {@link javax.management.ValueExp} to be found
     */
    private javax.management.ValueExp val;

    /**
     * @serial The array of {@link javax.management.ValueExp} to be searched
     */
    private javax.management.ValueExp[]  valueList;


    /**
     * Basic Constructor.
     */
    public InQueryExp() {
    }

    /**
     * Creates a new InQueryExp with the specified ValueExp to be found in
     * a specified array of ValueExp.
     */
    public InQueryExp(javax.management.ValueExp v1, javax.management.ValueExp items[]) {
        val       = v1;
        valueList = items;
    }


    /**
     * Returns the checked value of the query.
     */
    public javax.management.ValueExp getCheckedValue()  {
        return val;
    }

    /**
     * Returns the array of values of the query.
     */
    public javax.management.ValueExp[] getExplicitValues()  {
        return valueList;
    }

    /**
     * Applies the InQueryExp on a MBean.
     *
     * @param name The name of the MBean on which the InQueryExp will be applied.
     *
     * @return  True if the query was successfully applied to the MBean, false otherwise.
     *
     * @exception BadStringOperationException
     * @exception BadBinaryOpValueExpException
     * @exception BadAttributeValueExpException
     * @exception InvalidApplicationException
     */
    public boolean apply(ObjectName name)
    throws BadStringOperationException, BadBinaryOpValueExpException,
        BadAttributeValueExpException, InvalidApplicationException  {
        if (valueList != null) {
            javax.management.ValueExp v      = val.apply(name);
            boolean numeric = v instanceof NumericValueExp;

            for (ValueExp element : valueList) {
                element = element.apply(name);
                if (numeric) {
                    if (((NumericValueExp) element).doubleValue() ==
                        ((NumericValueExp) v).doubleValue()) {
                        return true;
                    }
                } else {
                    if (((StringValueExp) element).getValue().equals(
                        ((StringValueExp) v).getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return val + " in (" + generateValueList() + ")";
    }


    private String generateValueList() {
        if (valueList == null || valueList.length == 0) {
            return "";
        }

        final StringBuilder result =
                new StringBuilder(valueList[0].toString());

        for (int i = 1; i < valueList.length; i++) {
            result.append(", ");
            result.append(valueList[i]);
        }

        return result.toString();
    }

 }
