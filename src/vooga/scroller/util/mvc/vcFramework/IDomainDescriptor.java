package vooga.scroller.util.mvc.vcFramework;


public interface IDomainDescriptor {
    
    String getDomainName();
    
    Tools<? extends IDomainDescriptor> getDomainTools();
    
    

}
