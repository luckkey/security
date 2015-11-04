package namoo.security.exam.domain.security;

import java.io.Serializable;

/**
 * PMS에서 사용되는 모든 역할을 표현하기 위해 사용되는 역할 정보 클래스.
 * 
 * @author Bee
 * @since 2009. 06. 29
 */
public class PMSRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5056021990410047586L;

	/** 역할 ID */
	private String id;

	/** 역할 설명 */
	private String note;

	/** 역할별 기본 페이지 */
	private String defaultPageURI;

	/** 사용자별 역할 사용 유무 */
	private boolean checked = false;

	/** 해당 역할이 적용되는 프로젝트의 ID */
	private Long projectId;
	/** 해당 역할이 적용되는 프로그램의 ID */
	private Long programId;

	/** 전체 프로젝트 적용 여부 */
	private String allBizYn;

	/** 역할 유형 */
	private String type;
	
	/** 역할 우선순위 : 여러 역할을 갖는 경우 우선순위에 따른 비즈니스 처리를 위한 속성 */
	private Integer priority;

	public PMSRole() {

	}

	public PMSRole(String roleId) {
		this(roleId, null);
	}

	public PMSRole(String roleId, String roleType) {
		this.id = roleId;
		this.type = roleType;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            설정할 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            설정할 note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return defaultPageURI
	 */
	public String getDefaultPageURI() {
		return defaultPageURI;
	}

	/**
	 * @param defaultPageURI
	 *            설정할 defaultPageURI
	 */
	public void setDefaultPageURI(String defaultPageURI) {
		this.defaultPageURI = defaultPageURI;
	}

	/**
	 * @return checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            설정할 checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            설정할 projectId
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return allBizYn
	 */
	public String getAllBizYn() {
		return allBizYn;
	}

	/**
	 * @param allBizYn
	 *            설정할 allBizYn
	 */
	public void setAllBizYn(String allBizYn) {
		this.allBizYn = allBizYn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
