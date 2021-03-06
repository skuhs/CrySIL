package org.crysil.protocol.payload.crypto.encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.crysil.logging.Logger;
import org.crysil.protocol.payload.PayloadRequest;
import org.crysil.protocol.payload.crypto.PayloadWithKey;
import org.crysil.protocol.payload.crypto.key.Key;

import com.google.common.io.BaseEncoding;

public class PayloadEncryptRequest extends PayloadRequest implements PayloadWithKey {

  /** The encryption keys. */
  protected List<Key>    encryptionKeys = new ArrayList<>();

  /** The algorithm. */
  protected String       algorithm;

  /** The plain data. */
  protected List<String> plainData      = new ArrayList<>();

  @Override
  public String getType() {
    return "encryptRequest";
  }

  /**
   * Gets the encryption keys.
   *
   * @return the encryption keys
   */
  public List<Key> getEncryptionKeys() {
    return encryptionKeys;
  }

  /**
   * Sets the encryption keys.
   *
   * @param encryptionKeys
   *          the new encryption keys
   */
  public void setEncryptionKeys(final List<Key> encryptionKeys) {
    clearEncryptionKeys();

    for (final Key current : encryptionKeys) {
      addEncryptionKey(current);
    }
  }

  /**
   * clear the list of encryption keys
   */
  public void clearEncryptionKeys() {
    this.encryptionKeys.clear();
  }

  /**
   * add a new key to the list
   *
   * @param encryptionKey
   */
  public void addEncryptionKey(final Key encryptionKey) {
    this.encryptionKeys.add(encryptionKey);
  }

  /**
   * Gets the algorithm.
   *
   * @return the algorithm
   */
  public String getAlgorithm() {
    return algorithm;
  }

  /**
   * Sets the algorithm.
   *
   * @param algorithm
   *          the new algorithm
   */
  public void setAlgorithm(final String algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Gets the plain data.
   *
   * @return the plain data
   */
  public List<byte[]> getPlainData() {
    final List<byte[]> tmp = new ArrayList<>();
    for (final String current : plainData) {
      tmp.add(BaseEncoding.base64().decode(current));
    }
    return tmp;
  }

  /**
   * Sets the plain data.
   *
   * @param plainData
   *          the new plain data
   */
  public void setPlainData(final List<byte[]> plainData) {
    clearPlainData();

    for (final byte[] current : plainData) {
      addPlainData(current);
    }
  }

  /**
   * clear the list of plain data
   */
  public void clearPlainData() {
    plainData.clear();
  }

  /**
   * add plain data to the list
   *
   * @param plainData
   */
  public void addPlainData(final byte[] plainData) {
    this.plainData.add(BaseEncoding.base64().encode(plainData));
  }

  @Override
  public List<Key> getKeys() {
    return getEncryptionKeys();
  }

  @Override
  public PayloadRequest getBlankedClone() {
    final PayloadEncryptRequest result = new PayloadEncryptRequest();
    final List<Key> keys = new ArrayList<>();
    for (final Key current : encryptionKeys) {
      keys.add(current.getBlankedClone());
    }
    result.encryptionKeys = keys;
    result.algorithm = Logger.isDebugEnabled() ? algorithm : "*****";
    final List<String> data = new ArrayList<>();
    for (final String current : plainData) {
      data.add(Logger.isDebugEnabled() ? current : "*****");
    }
    result.plainData = data;
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final PayloadEncryptRequest other = (PayloadEncryptRequest) obj;
    if (algorithm == null) {
      if (other.algorithm != null) {
        return false;
      }
    } else if (!algorithm.equals(other.algorithm)) {
      return false;
    }
    if (encryptionKeys == null) {
      if (other.encryptionKeys != null) {
        return false;
      }
    } else if (!encryptionKeys.equals(other.encryptionKeys)) {
      return false;
    }
    if (plainData == null) {
      if (other.plainData != null) {
        return false;
      }
    } else if (!plainData.equals(other.plainData)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
   return Arrays.hashCode(new Object[]{type,encryptionKeys,algorithm,plainData});
  }
}
