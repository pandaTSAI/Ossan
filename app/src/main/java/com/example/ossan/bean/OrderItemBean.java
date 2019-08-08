package com.example.ossan.bean;

import java.io.Serializable;

public class OrderItemBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer seqNo;
    private int quantity;
    private int unitPrice;
    private Double discount;
    private OssanBean ossanBean ;
    private OrderBean orderBean ;
    public OrderItemBean(Integer seqNo, int quantity, int unitPrice, Double discount) {
        super();
        this.seqNo = seqNo;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
    public OrderItemBean() {

    }

    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public OssanBean getOssanBean() {
        return ossanBean;
    }
    public void setOssanBean(OssanBean ossanBean) {
        this.ossanBean = ossanBean;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }
    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }


}
