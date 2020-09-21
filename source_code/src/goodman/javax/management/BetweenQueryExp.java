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
import goodman.javax.management.ObjectName;
import goodman.javax.management.QueryEval;
import goodman.javax.management.QueryExp;
import goodman.javax.management.StringValueExp;
import goodman.javax.management.ValueExp;

/**
 * This class is used by the query-building mechanism to represent binary
 * relations.
 * @serial include
 *
 * @since 1.5
 */
class BetweenQueryExp extends QueryEval implements QueryExp {

    /* Serial version */
    private static final long serialVersionUID = -2933597532866307444L;

    /**
     * @serial The checked value
     */
    private javax.management.ValueExp exp1;

    /**
     * @serial The lower bound value
     */
    private javax.management.ValueExp exp2;

    /**
     * @serial The upper bound value
     */
    private javax.management.ValueExp exp3;


    /**
     * Basic Constructor.
     */
    public BetweenQueryExp() {
    }

    /**
     * Creates a new BetweenQueryExp with v1 checked value, v2 lower bound
     * and v3 upper bound values.
     */
    public BetweenQueryExp(javax.management.ValueExp v1, javax.management.ValueExp v2, javax.management.ValueExp v3) {
        exp1  = v1;
        exp2  = v2;
        exp3  = v3;
    }


    /**
     * Returns the checked value of the query.
     */
    public javax.management.ValueExp getCheckedValue()  {
        return exp1;
    }

    /**
     * Returns the lower bound value of the query.
     */
    public javax.management.ValueExp getLowerBound()  {
        return exp2;
    }

    /**
     * Returns the upper bound value of the query.
     */
    public javax.management.ValueExp getUpperBound()  {
        return exp3;
    }

    /**
     * Applies the BetweenQueryExp on an MBean.
     *
     * @param name The name of the MBean on which the BetweenQueryExp will be applied.
     *
     * @return  True if the query was successfully applied to the MBean, false otherwise.
     *
     * @exception BadStringOperationException
     * @exception BadBinaryOpValueExpException
     * @exception BadAttributeValueExpException
     * @exception InvalidApplicationException
     */
    public boolean apply(ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException,
        BadAttributeValueExpException, InvalidApplicationException  {
        javax.management.ValueExp val1 = exp1.apply(name);
        javax.management.ValueExp val2 = exp2.apply(name);
        ValueExp val3 = exp3.apply(name);
        boolean numeric = val1 instanceof NumericValueExp;

        if (numeric) {
            if (((NumericValueExp)val1).isLong()) {
                long lval1 = ((NumericValueExp)val1).longValue();
                long lval2 = ((NumericValueExp)val2).longValue();
                long lval3 = ((NumericValueExp)val3).longValue();
                return lval2 <= lval1 && lval1 <= lval3;
            } else {
                double dval1 = ((NumericValueExp)val1).doubleValue();
                double dval2 = ((NumericValueExp)val2).doubleValue();
                double dval3 = ((NumericValueExp)val3).doubleValue();
                return dval2 <= dval1 && dval1 <= dval3;
            }

        } else {
            String sval1 = ((javax.management.StringValueExp)val1).getValue();
            String sval2 = ((javax.management.StringValueExp)val2).getValue();
            String sval3 = ((StringValueExp)val3).getValue();
            return sval2.compareTo(sval1) <= 0 && sval1.compareTo(sval3) <= 0;
        }
    }

    /**
     * Returns the string representing the object.
     */
    @Override
    public String toString()  {
        return "(" + exp1 + ") between (" + exp2 + ") and (" + exp3 + ")";
    }
}
