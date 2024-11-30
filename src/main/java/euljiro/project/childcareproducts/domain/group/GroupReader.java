package euljiro.project.childcareproducts.domain.group;

public interface GroupReader {

    Group findByGroupToken(String itemToken);
}
