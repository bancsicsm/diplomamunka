// Generated by Dagger (https://dagger.dev).
package demo.app.paintball.data.rest;

import dagger.internal.Factory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RestServiceImpl_Factory implements Factory<RestServiceImpl> {
  @Override
  public RestServiceImpl get() {
    return newInstance();
  }

  public static RestServiceImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RestServiceImpl newInstance() {
    return new RestServiceImpl();
  }

  private static final class InstanceHolder {
    private static final RestServiceImpl_Factory INSTANCE = new RestServiceImpl_Factory();
  }
}