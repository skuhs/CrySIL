package org.crysil.protocol.payload.auth;

import java.util.Arrays;

import org.crysil.protocol.payload.PayloadRequest;

/**
 * Container for answering authentication challenges. Thus, mostly issued by clients.
 */
public class PayloadAuthRequest extends PayloadRequest {

	/** The authentication data. */
	protected AuthInfo authInfo;

	@Override
	public String getType() {
		return "authChallengeResponse";
	}

	/**
	 * Gets the authentication data.
	 *
	 * @return the authentication data
	 */
	public AuthInfo getAuthInfo() {
		return authInfo;
	}

	/**
	 * Sets the authentication data.
	 *
	 * @param authInfo
	 *            the authentication data
	 */
	public void setAuthInfo(final AuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	@Override
	public PayloadRequest getBlankedClone() {
		final PayloadAuthRequest result = new PayloadAuthRequest();
		result.authInfo = authInfo.getBlankedClone();

		return result;
	}
	@Override
  public int hashCode() {
   return Arrays.hashCode(new Object[]{type,authInfo});
  }

}
