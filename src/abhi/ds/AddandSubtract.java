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
	public synchronized Integer Add(int x, int y) {
		// TODO Auto-generated method stub
		return new Integer(x+y);
	}

	@Override
	public synchronized Integer Subtract(int a, int b) {
		// TODO Auto-generated method stub
		return new Integer(a-b);
	}
	

}
