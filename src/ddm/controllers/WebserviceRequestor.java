package ddm.controllers;


public interface WebserviceRequestor<T> {
	void onNetworkSuccess(T result);
	void onNetworkFail(WebserviceException caughtException);
}
