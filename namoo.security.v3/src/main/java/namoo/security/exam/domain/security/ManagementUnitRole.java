package namoo.security.exam.domain.security;


/**
 * 관리대상에 대한 사용자의 역할정보
 * 
 * @author kyyoon
 * @since 2011. 5. 11.
 */
public class ManagementUnitRole {
	private Long userNo;
	
	// FIXME 관리대상 제외
//	private ManagementUnitMeta unit;
	
	private String roleId;
	
	private String roleNote;
	
	private Integer rolePriority;
	
	public ManagementUnitRole(){
		
	}
	
	public ManagementUnitRole(Long userNo, String roleId){
		this.userNo = userNo;
		this.roleId = roleId;
	}
	
//	public ManagementUnitRole(Long userNo, ManagementUnitMeta unit, String roleId){
//		this.userNo = userNo;
//		this.unit = unit;
//		this.roleId = roleId;
//	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

//	public ManagementUnitMeta getUnit() {
//		return unit;
//	}
//
//	public void setUnit(ManagementUnitMeta unit) {
//		this.unit = unit;
//	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleNote() {
		return roleNote;
	}

	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}

	public Integer getRolePriority() {
		return rolePriority;
	}

	public void setRolePriority(Integer rolePriority) {
		this.rolePriority = rolePriority;
	}
	
}
