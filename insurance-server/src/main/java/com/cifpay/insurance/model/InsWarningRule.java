package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsWarningRule implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	/** id **/
	private Integer id;
	/** 规则编号 **/
	private String code;
	/** 规则名称 **/
	private String name;
	/** 绿灯最小值 **/
	private Double greenMin;
	/** 绿灯最大值 **/
	private Double greenMax;
	/** 黄灯最小值 **/
	private Double yellowMin;
	/** 黄灯最大值 **/
	private Double yellowMax;
	/** 红灯最小值 **/
	private Double redMin;
	/** 红灯最大值 **/
	private Double redMax;
	/** 是否启用（1是；0否） **/
	private Integer isEnable;
	/** 备注 **/
	private String remark;
	/** 创建人 **/
	private String createdUser;
	/** 创建时间 **/
	private Date createdTime;
	/** 修改人 **/
	private String modifiedUser;
	/** 修改时间 **/
	private Date modifiedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getGreenMin() {
		return greenMin;
	}

	public void setGreenMin(Double greenMin) {
		this.greenMin = greenMin;
	}

	public Double getGreenMax() {
		return greenMax;
	}

	public void setGreenMax(Double greenMax) {
		this.greenMax = greenMax;
	}

	public Double getYellowMin() {
		return yellowMin;
	}

	public void setYellowMin(Double yellowMin) {
		this.yellowMin = yellowMin;
	}

	public Double getYellowMax() {
		return yellowMax;
	}

	public void setYellowMax(Double yellowMax) {
		this.yellowMax = yellowMax;
	}

	public Double getRedMin() {
		return redMin;
	}

	public void setRedMin(Double redMin) {
		this.redMin = redMin;
	}

	public Double getRedMax() {
		return redMax;
	}

	public void setRedMax(Double redMax) {
		this.redMax = redMax;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
