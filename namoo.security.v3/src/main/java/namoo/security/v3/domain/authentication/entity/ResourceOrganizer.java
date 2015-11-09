
package namoo.security.v3.domain.authentication.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 자원을 객체 구조로 변환해주기 위한 클래스이다. 데이터베이스에서 조회된 메뉴나 화면등의 자원정보를 상/하위 관계에 맞게 계층화 시켜준다.
 * 
 * @author Bee
 * @since 2009. 07. 01
 */
public class ResourceOrganizer {

    /** 메뉴 목록 */
    private List<PMSMenu> resources;

    /** 메뉴아이디 별로 보관된 메뉴 */
    private Map<Long, PMSMenu> rscMap;

    /** 메뉴 아이디 별로 보관된 형제 메뉴 목록 */
    private Map<Long, List<PMSMenu>> siblingMap;

    /** 메뉴 정렬을 위한 메뉴 아이디 비교 comparator */
    private ResourceSeqComparator comparator = new ResourceSeqComparator();

    /** 최상위 메뉴 */
    private PMSMenu rootResource;

    /**
     * 생성자, 메뉴 정보를 입력받아 객체 구조화한다.
     * @param aResources 메뉴 목록 정보
     */
    public ResourceOrganizer(List<PMSMenu> aResources) {
        this.resources = aResources;
        init();
    }

    /**
     * 메뉴 구조를 생성하는 필요한 초기화 작업을 수행한다. 각종 변수 초기화를 수행하고, rscMap과 siblingMap을 구성한다.
     */
    public void init() {

        if (resources == null || resources.isEmpty()) {
//            throw PMSExceptionFactory.createRuntime("SC00300005");
        	return;
        }

        int capacity = (int) (resources.size() / 0.75F) + 1;
        this.rscMap = new HashMap<Long, PMSMenu>(capacity);
        this.siblingMap = new HashMap<Long, List<PMSMenu>>();

        for (PMSMenu rsc : resources) {
            this.rscMap.put(rsc.getResourceId(), rsc);

            List<PMSMenu> sibling = findSibling(rsc.getParentResourceId());
            if (sibling != null) {
                sibling.add(rsc);
            }
        }

        this.rootResource = makeRoot();
    }

    /**
     * 트리형태로 구성된 메뉴구조를 펼쳐서 List형태로 변환한다.
     * 
     * @return 리스트 형태의 메뉴 구조(1차원)
     */
    public List<PMSMenu> listAll() {
        return makeFlat(this.rootResource, 0);
    }

    /**
     * 트리형태로 구성된 메뉴구조를 펼쳐서 List형태로 변환한다.
     * 
     * @param root
     *            루트 메뉴
     * @param depth
     *            메뉴 깊이
     * @return 리스트 형태의 메뉴 구조(1차원)
     */
    private List<PMSMenu> makeFlat(PMSMenu root, int depth) {

        List<PMSMenu> result = new ArrayList<PMSMenu>();

        // Guard condition
        if (root == null || root.getSubResources() == null || root.getSubResources().isEmpty()) {
            return result;
        }

        for (PMSMenu resource : root.getSubResources()) {
            result.add(resource);
            result.addAll(makeFlat(resource, depth));
        }

        return result;
    }

    /**
     * 아규먼트로 넘어온 메뉴 아이디와 같은 깊이의 sibling 메뉴 목록을 구성한다.
     * 
     * @param parentResourceId
     *            메뉴 아이디
     * @return sibling 메뉴 목록
     */
    private List<PMSMenu> findSibling(Long parentResourceId) {
        if (parentResourceId == null) {
            return null;
        }

        List<PMSMenu> sibling = this.siblingMap.get(parentResourceId);
        if (sibling == null) {
            sibling = new ArrayList<PMSMenu>();
            this.siblingMap.put(parentResourceId, sibling);
        }

        return sibling;
    }

    /**
     * 최상위 메뉴 노드를 생성한다.
     * 
     * @return 최상위 메뉴 노드
     */
    public PMSMenu makeRoot() {

        int depth = 1;
        List<PMSMenu> roots = findRootResources();
        for (PMSMenu root : roots) {
            root.setDepth(depth);
            construtResources(root, depth);
        }

        PMSMenu newRootResource = new PMSMenu();
        newRootResource.setName("ROOT");
        newRootResource.setSubResources(roots);
        newRootResource.setResourceId(0L);

        for (PMSMenu resource : roots) {
            resource.setParentResource(newRootResource);
        }

        return newRootResource;
    }

    /**
     * 최상위 메뉴 노드를 반환한다.
     * 
     * @return 최상위 메뉴 노드
     */
    public PMSMenu getRootMenu() {
        return this.rootResource;
    }

    /**
     * 최상위 메뉴부터 시작하는 전체 메뉴 구조를 생성한다.
     * 
     * @param root
     *            최상위 메뉴 노드
     */
    private void construtResources(PMSMenu root, int depth) {
        // children을 찾는다.
        List<PMSMenu> children = this.siblingMap.get(root.getResourceId());
        if (children == null) {
            return;
        }

        Collections.sort(children, this.comparator);
        depth = depth + 1;

        for (PMSMenu childResource : children) {
            root.addChild(childResource);
            childResource.setDepth(depth);
            childResource.setParentResource(root);

        }
        for (PMSMenu child : root.getSubResources()) {
            construtResources(child, depth);
        }
    }

    /**
     * 루트 노드를 제외한 최상위 레벨의 메뉴 목록을 반환한다.
     * 
     * @return 루트 노드를 제외한 최상위 레벨의 메뉴 목록
     */
    private List<PMSMenu> findRootResources() {

        List<PMSMenu> roots = new ArrayList<PMSMenu>();
        for (PMSMenu resource : this.rscMap.values()) {
            if (resource.getParentResourceId() == null || resource.getParentResourceId() == 0) {
                roots.add(resource);
            }
        }

        Collections.sort(roots, this.comparator);
        return roots;
    }

}
