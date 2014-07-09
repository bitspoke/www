package modules

import scaldi.Module
import services.{FakeMongoService, MongoService}


class TestModule extends Module {
  bind[MongoService] to new FakeMongoService // RealMongoDatabase
}
