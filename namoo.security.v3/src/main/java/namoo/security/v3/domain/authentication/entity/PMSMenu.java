
package namoo.security.v3.domain.authentication.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * PMIS의 각종 자원중 메뉴 자원을 표현하기 위한 BDT클래스이며, PMISResource클래스를 상속받는다. 따라서 PMISResource 클래스의 기본 특성을 그대로 가지며, 여기에 추가형 메뉴순서,
 * 하위메뉴, 상위메뉴등의 속성을 갖는다.
 * 
 * @author Bee
 * @since 2009. 06. 30
 */
public class PMSMenu extends PMSResource {

    private static final long serialVersionUID = -6444404390361090671L;

    /** 자원 순서 */
    private int sequence;

    /** 하위 자원 */
    private List<PMSMenu> subResources;

    /** 상위 자원 */
    private PMSMenu parentResource;

    /** 깊이 */
    private int depth;
    
    /** 도움말 존재 여부 */
    private boolean hasHelp;

    public PMSMenu() {
        //
        this.subResources = new ArrayList<PMSMenu>();
        this.hasHelp = false;
    }

    public PMSMenu(Long resourceId) {
        //
        super(resourceId);
        this.subResources = new ArrayList<PMSMenu>();
        this.hasHelp = false;
    }

    public PMSMenu(Long resourceId, PMSMenu parentResource) {
        //
        this(resourceId);
        this.parentResource = parentResource;
    }

    public String getFullName() {

        String name = "";

        if (this.parentResource != null) {
            name = this.parentResource.getFullName();
        }

        if (!"".equals(name)) {
            name += " > ";
        }
        return name + getName();
    }

    // helper method
    public Long getParentResourceId() {
        return parentResource == null ? null : parentResource.getResourceId();
    }

    /**
     * @return sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     *            설정할 sequence
     */
    public void setSequence(int sequence) {
    	isMutable();
        this.sequence = sequence;
    }

    /**
     * @return subResources
     */
    public List<PMSMenu> getSubResources() {
        return subResources;
    }

    /**
     * @param subResources
     *            설정할 subResources
     */
    public void setSubResources(List<PMSMenu> subResources) {
    	isMutable();
        this.subResources = subResources;
    }

    /**
     * @param resource
     *            하위 메뉴를 추가한다.
     */
    public void addChild(PMSMenu resource) {
    	isMutable();
        if (this.subResources == null) {
            this.subResources = new ArrayList<PMSMenu>();
        }

        this.subResources.add(resource);

    }

    /**
     * @return parentResource
     */
    public PMSMenu getParentResource() {
        return parentResource;
    }

    /**
     * 상위의 모든 메뉴중 index 번째를 반환한다.
     * 
     * @param index
     *            인덱스. 0부터 시작
     * @return index 번째 메뉴
     */
    public PMSMenu getParentResource(int index) {

        if (getDepth() == index) {
            return this;
        }

        if (this.parentResource == null) {
            return null;
        }
        
        List<PMSMenu> results = getAllParent();
        if (results.size() < index + 1) {
            return null;
        }

        return results.get(index);
    }

    /**
     * 상위의 모든 메뉴를 반환한다. 순서는 상위부터
     * 
     * @return 상위의 모든 메뉴
     */
    public List<PMSMenu> getAllParent() {
        List<PMSMenu> results = new ArrayList<PMSMenu>();
        findAllParentRecursive(results);
        return results;
    }

    /**
     * 상위의 모든 메뉴를 반환한다
     * 
     * @param results
     *            결과를 담을 List
     */
    private void findAllParentRecursive(List<PMSMenu> results) {
        if (this.parentResource != null) {
            this.parentResource.findAllParentRecursive(results);
            results.add(this.parentResource);
        }
    }

    /**
     * @param parentResource
     *            설정할 parentResource
     */
    public void setParentResource(PMSMenu parentResource) {
    	isMutable();
        this.parentResource = parentResource;
    }

    /**
     * @return depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth
     *            설정할 depth
     */
    public void setDepth(int depth) {
    	isMutable();
        this.depth = depth;
    }

    public boolean isLeaf() {
        return this.subResources != null ? this.subResources.isEmpty() : true;
    }

    public PMSMenu getChild(int index) {
        if (this.subResources == null) {
            return null;
        }
        if (this.subResources.size() < index + 1) {
            return null;
        }
        return this.subResources.get(index);
    }

    /**
     * 페이지 메타정보를 이용해서 메뉴를 찾는다.<br>
     * 자신과 자식들 중 메뉴식별자로 재귀적으로 메뉴를 검색
     * 
     * @param menuUrn
     *            메뉴식별자
     * @return 검색한 메뉴. 없을 경우 null
     */
    public PMSMenu findMenu(String menuUrn) {
        if (menuUrn == null) {
            return null;
        }

        if (menuUrn.equals(this.getUrn())) {
            return this;
        }

        PMSMenu result = null;
        for (PMSMenu child : this.subResources) {
            result = child.findMenu(menuUrn);
            if (result != null) {
                break;
            }
        }

        return result;
    }

    public String getAdjustedUri() {
    	PMSMenu adjustedMenu = getAdjustedMenu();
    	return adjustedMenu != null ? adjustedMenu.getUri() : "#";
    }
    
    public String getAdjustedUrn() {
    	PMSMenu adjustedMenu = getAdjustedMenu();
    	return adjustedMenu != null ? adjustedMenu.getUrn() : null;
    }
    
    public PMSMenu getAdjustedMenu() {
    	if (super.getUri().startsWith("{") && super.getUri().endsWith("}")) {
    		Integer index = findIndex(super.getUri());
    		if (subResources == null || subResources.size() < index) {
    			return null;
    		}
    		return subResources.get(index - 1).getAdjustedMenu();
    	}
    	
    	return this;
    }
    
    public PMSMenu getAdjustedMenuWithoutDynamic() {
    	if (super.getUri().startsWith("{") && super.getUri().endsWith("}")) {
    		Integer index = findIndex(super.getUri());
    		
    		List<PMSMenu> subResourceWithoutDynamic = new ArrayList<PMSMenu>();
    		
    		for (PMSMenu pmsMenu : subResources) {
    			if(!pmsMenu.isDynamic()) subResourceWithoutDynamic.add(pmsMenu); 
    		}
    		
    		if (subResourceWithoutDynamic.size() < index) {
    			return null;
    		}
    		
    		PMSMenu result = subResourceWithoutDynamic.get(index - 1).getAdjustedMenuWithoutDynamic();
    		
    		// 만일 동적메뉴의 adjustedMenu를 못찾았다면
    		// 서브메뉴 어디서라도 정적메뉴 끝단을 찾는다.
    		// 예) 정적메뉴 {1} 끝에 동적메뉴 빈 것 하나 붙는 경우
    		if (result == null) {
    			for (PMSMenu pmsMenu : subResourceWithoutDynamic) {
					result = pmsMenu.getAdjustedMenuWithoutDynamic();
					if (result != null) break;
				}
    		}
    		
    		return result;
    	}
    	
    	return this;
    }

    /**
     * {1} 형식의 URI 값에서 숫자를 찾아서 반환한다.
     * 
     * @param uri
     *            URI
     * @return 숫자값
     */
    protected Integer findIndex(String uri) {
        try {
            return Integer.valueOf(uri.substring(1, uri.indexOf("}")));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * 동적메뉴 여부
     * 
     * @return
     */
    public boolean isDynamic(){
    	String kind = getKind();
    	if(kind == null || kind.length()<=0)
    		return false;
    	return RES_DYNA_MENU.equals(kind);
    }
    
    /**
     * 인덱싱된 URI를 갖으며 대상 인덱스의 메뉴가 동적메뉴인지 확인한다.
     * 
     * @return
     */
    public boolean isIndexedDynaSubMenu(){
    	if (super.getUri().startsWith("{") && super.getUri().endsWith("}")){
    		Integer index = findIndex(super.getUri());
            if (subResources == null || subResources.size() < index) {
                return false;
            }
            PMSMenu indexMenu = subResources.get(index - 1);
            if(indexMenu==null)
            	return false;
            return RES_DYNA_MENU.equals(indexMenu.getKind());
    	}
    	return false;
    }

	/**
	 * 선택된 메뉴의 아이디로 메뉴를 찾는다.
	 * 
	 * @param reousrceId
	 * @return
	 */
	public PMSMenu findMenu(Long reousrceId) {
		if (reousrceId == null) {
            return null;
        }

        if (reousrceId.equals(this.getResourceId())) {
            return this;
        }

        PMSMenu result = null;
        for (PMSMenu child : this.subResources) {
            result = child.findMenu(reousrceId);
            if (result != null) {
                break;
            }
        }

        return result;
	}
	
	@Override
	public void immutable() {
		super.immutable();
		if(this.subResources != null){
			for (PMSMenu child : this.subResources) {
				child.immutable();
			}
		}
	}
	
	public boolean isHasHelp() {
		return hasHelp;
	}
	
	public void setHasHelp(boolean hasHelp) {
		this.hasHelp = hasHelp;
	}
	
	public boolean isHasHelpRecursive() {
		return (this.hasHelp ? true : hashHelpchildren());
	}

	private boolean hashHelpchildren() {
		if (this.subResources == null || this.subResources.isEmpty()) return false;
		for (PMSMenu subResource : this.subResources) {
			if (subResource.isHasHelpRecursive()) return true;
		}
		return false;
	}

}
