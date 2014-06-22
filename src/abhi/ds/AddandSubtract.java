/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma
 *
 */
public class AddandSubtract implements IAddandSubtract, IDistributedObject {

	@Override
	public Integer Add(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return new Integer(x+y);
	}

	@Override
	public Integer Subtract(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return new Integer(a-b);
	}
	

}
