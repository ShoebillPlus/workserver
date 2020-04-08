package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author yanwei
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
@ApiModel(value="SysUser对象", description="人员信息")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "openid", value = "用户唯一标识")
    private String openid;

    @ApiModelProperty(name = "name", value = "姓名(*)")
    private String name;

    @ApiModelProperty(name = "card_id", value = "身份证号")
    private String cardId;

    @ApiModelProperty(name = "birthday", value = "生日")
    private String birthday;

    @ApiModelProperty(name = "phone", value = "手机号(*)")
    private String phone;

    @ApiModelProperty(name = "sex", value = "性别")
    private Integer sex;

    @ApiModelProperty(name = "login_account", value = "登录账号(*)")
    private String loginAccount;

    @ApiModelProperty(name = "login_pass", value = "登录密码")
    private String loginPass;

    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;

    @ApiModelProperty(name = "type", value = "类型(*)")
    private Integer type;

    @ApiModelProperty(name = "dept_id", value = "部门编号（，间隔）")
    @TableField(exist = false)
    private String deptId;

    @ApiModelProperty(name = "dept_name", value = "部门名称（，间隔）")
    @TableField(exist = false)
    private String deptName;

    @ApiModelProperty(name = "role_id", value = "角色编号（，间隔）")
    @TableField(exist = false)
    private String roleId;

    @ApiModelProperty(name = "role_name", value = "角色名称（，间隔）")
    @TableField(exist = false)
    private String roleName;

    @ApiModelProperty(name = "is_valid", value = "是否有效")
    @TableField(value = "is_valid", fill = FieldFill.INSERT)
    private Integer isValid;

    @ApiModelProperty(name = "account_non_expired", value = "过期性  1没过期0过期")
    private Integer accountNonExpired;

    @ApiModelProperty(name = "credentials_non_expired", value = "有效性  1有效0失效")
    private Integer credentialsNonExpired;

    @ApiModelProperty(name = "account_non_locked", value = "锁定性 1未锁定0锁定")

    private Integer accountNonLocked;

    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

    @ApiModelProperty(name = "isKpi", value = "是否被评论")
    @TableField(exist = false)
    private int isKpi;

    @ApiModelProperty(name = "ruleId", value = "适用规则")
    @TableField(exist = false)
    private Long ruleId;

}
