package modules

import scaldi.Module
import services.{FakeMongo, Mongo}


class TestModule extends Module {
  bind[Mongo] to new FakeMongo // RealMongoDatabase
}
