package namoo.security.v3.domain.authentication.entity;

import java.util.Comparator;

public class  ResourceSeqComparator implements Comparator<PMSMenu> {

    public int compare(PMSMenu rsc1, PMSMenu rsc2) {
        return rsc1.getSequence() - rsc2.getSequence();
    }
}
