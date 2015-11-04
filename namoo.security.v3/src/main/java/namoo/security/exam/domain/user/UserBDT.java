package namoo.security.exam.domain.user;

import java.io.Serializable;
import java.util.Date;

public class UserBDT implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2436164332080048933L;

    /** 사용자번호 */
    private Long userNo;

    // TODO 확장 필요 
    /** 회사정보 */
    // private CompanyBDT company;

    /** 부서정보 */
    // private DepartmentBDT department;

    /** 이름 */
    private String name;

    /** 전화번호 */
    private String telephoneNumber;

    /** 이메일 */
    private String email;

    /** 직위 */
    private String position;

    /** 생성일 */
    private Date regDate;

    /** 변경일 */
    private Date modDate;

    // TODO 확장 필요
    /** 이미지 */
    // private Image image;

    /** 썸네일이미지 */
    // private Image thumbnailImage;

    /** 이미지유형 */
    private String imageType;

    /** 최종사진수정일 */
    private Date imageUpdatedDate;

    /** 현재경력등급 */
    private String engineerRating;

    /** 현재경력년수 */
    private int workExperienceYear;

    /** 현재경력개월수 */
    private int workExperienceMonth;

    /** 보유기술 */
    private String skill;

    /** 과정명 */
    private String projectName;

    /** 역할명 */
    private String roleName;

    /** 닉네임 */
    private String nickname;

    /** 학년 */
    private String grade;

    /** 반 */
    private String classNumber;

    public UserBDT() {
    }

    public UserBDT(Long userNo) {
        this.userNo = userNo;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    // public Image getImage() {
    // return image;
    // }
    //
    // public void setImage(Image image) {
    // this.image = image;
    // }
    //
    // public Image getThumbnailImage() {
    // return thumbnailImage;
    // }
    //
    // public void setThumbnailImage(Image thumbnailImage) {
    // this.thumbnailImage = thumbnailImage;
    // }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Date getImageUpdatedDate() {
        return imageUpdatedDate;
    }

    public void setImageUpdatedDate(Date imageUpdatedDate) {
        this.imageUpdatedDate = imageUpdatedDate;
    }

    public String getEngineerRating() {
        return engineerRating;
    }

    public void setEngineerRating(String engineerRating) {
        this.engineerRating = engineerRating;
    }

    public int getWorkExperienceYear() {
        return workExperienceYear;
    }

    public void setWorkExperienceYear(int workExperienceYear) {
        this.workExperienceYear = workExperienceYear;
    }

    public int getWorkExperienceMonth() {
        return workExperienceMonth;
    }

    public void setWorkExperienceMonth(int workExperienceMonth) {
        this.workExperienceMonth = workExperienceMonth;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

//    public CompanyBDT getCompany() {
//        return company;
//    }
//
//    public void setCompany(CompanyBDT company) {
//        this.company = company;
//    }
//
//    public DepartmentBDT getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(DepartmentBDT department) {
//        this.department = department;
//    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
