package core.facade;

import core.exceptions.CouponsException;

public class CustomerFacade extends ClientFacade {

	private int id;

	public CustomerFacade(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	protected boolean login(String email, String password) throws CouponsException {
		// TODO Auto-generated method stub
		return false;
	}

}
