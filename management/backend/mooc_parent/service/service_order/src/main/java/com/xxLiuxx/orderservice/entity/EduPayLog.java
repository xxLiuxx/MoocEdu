package com.xxLiuxx.orderservice.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * payment log
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
@ApiModel(value="EduPayLog", description="payment log")
public class EduPayLog implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "order number")
    private String orderNo;

    @ApiModelProperty(value = "payment time")
    private Date payTime;

    @ApiModelProperty(value = "total fee")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "transaction id")
    private String transactionId;

    @ApiModelProperty(value = "order status")
    private String tradeState;

    @ApiModelProperty(value = "payment type（1：WeChat 2：Alipay")
    private Integer payType;

    @ApiModelProperty(value = "attr")
    private String attr;

    @ApiModelProperty(value = "logic delete 1（true）deleted， 0（false）not deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "create time")
    private Date gmtCreate;

    @ApiModelProperty(value = "update time")
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "EduPayLog{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", payTime=" + payTime +
        ", totalFee=" + totalFee +
        ", transactionId=" + transactionId +
        ", tradeState=" + tradeState +
        ", payType=" + payType +
        ", attr=" + attr +
        ", isDeleted=" + isDeleted +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
