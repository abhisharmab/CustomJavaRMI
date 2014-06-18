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
	public Integer Add(int x, int y) {
		// TODO Auto-generated method stub
		return new Integer(x+y);
	}

	@Override
	public Integer Subtract(int a, int b) {
		// TODO Auto-generated method stub
		return new Integer(a-b);
	}
	

}
