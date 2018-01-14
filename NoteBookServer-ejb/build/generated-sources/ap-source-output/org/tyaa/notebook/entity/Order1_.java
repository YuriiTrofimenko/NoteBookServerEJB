package org.tyaa.notebook.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tyaa.notebook.entity.State;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-09T11:49:29")
@StaticMetamodel(Order1.class)
public class Order1_ { 

    public static volatile SingularAttribute<Order1, Date> date;
    public static volatile SingularAttribute<Order1, State> stateId;
    public static volatile SingularAttribute<Order1, Integer> id;
    public static volatile SingularAttribute<Order1, String> text;
    public static volatile SingularAttribute<Order1, String> customer;

}