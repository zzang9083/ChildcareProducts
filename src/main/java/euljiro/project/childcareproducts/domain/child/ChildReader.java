package euljiro.project.childcareproducts.domain.child;

import java.util.*;


public interface ChildReader {

    List<Child> getAllChildBy(String userKey);
}
