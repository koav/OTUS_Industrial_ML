package otus.lessons.hw01.business


class LinearRegression {

  def fit(train_features, train_target): Unit {

    X = np.concatenate((np.ones((train_features.shape[0], 1)), train_features), axis=1)
    y = train_target
    w = np.linalg.inv(X.T.dot(X)).dot(X.T).dot(y)
    self.w = w[1:]
    self.w0 = w[0]

  }

  def predict(test_features): Unit = {
    test_features.dot(self.w) + self.w0
  }

}

