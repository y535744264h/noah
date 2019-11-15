package com.cctv.project.noah.demo.entity;

import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ApiModel("学生实体")
public class Student extends BaseEntity {

    @ApiModelProperty("学生序号")
    @Excel(name = "学生序号", cellType = Excel.ColumnType.NUMERIC, prompt = "学生序号")
    private Long studentId;

    @ApiModelProperty("学生姓名")
    @Excel(name = "学生姓名")
    @NotBlank(message = "学生姓名不能为空")
    private String studentName;

    @ApiModelProperty("学生年龄")
    @Excel(name = "学生年龄")
    private String studentAge;

    @ApiModelProperty("学生状态")
    @Excel(name = "学生状态",readConverterExp = "0=在校,1=离校,2=毕业")
    private String studentState;

    @ApiModelProperty("学生性别")
    @Excel(name = "学生性别",readConverterExp = "0=男,1=女,2=未知")
    private String studentSex;

    @ApiModelProperty("班级")
    @Excel(name = "班级",readConverterExp = "1=五年一班,2=五年二班,3=五年三班")
    private Long classId;

    @ApiModelProperty("入学日期")
    @Excel(name = "入学日期",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date admissionTime;

    @ApiModelProperty("毕业日期")
    @Excel(name = "毕业日期",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date graduationTime;

    private static final long serialVersionUID = 1L;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge == null ? null : studentAge.trim();
    }

    public String getStudentState() {
        return studentState;
    }

    public void setStudentState(String studentState) {
        this.studentState = studentState == null ? null : studentState.trim();
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex == null ? null : studentSex.trim();
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentId=").append(studentId);
        sb.append(", studentName=").append(studentName);
        sb.append(", studentAge=").append(studentAge);
        sb.append(", studentState=").append(studentState);
        sb.append(", studentSex=").append(studentSex);
        sb.append(", classId=").append(classId);
        sb.append(", admissionTime=").append(admissionTime);
        sb.append(", graduationTime=").append(graduationTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}