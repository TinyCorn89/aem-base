LaunchPad-2.0 Project
========

This a content package project generated using the multimodule-content-package-archetype.

Building
--------

This project uses Maven for building. Common commands:

mvn -e clean install -Dmaven.test.skip=true -Pdeploy -Dcq.port=4502 -Dcq.host=localhost
for mvn version 3.2 and above: mvn -e clean install -Dmaven.test.skip=true -Pdeploy -D"cq.port=4502" -D"cq.host=localhost"

From the root directory, run ``mvn -e clean install -Pdeploy -Dcq.port=4502 -Dcq.host=localhost`` to build the bundle and content package and install to a Author CQ instance.

From the root directory, run ``mvn -e clean install -Pdeploy -Dcq.port=4503 -Dcq.host=localhost`` to build the bundle and content package and install to a Publish CQ instance.


Using with VLT
--------------

To use vlt with this project, first build and install the package to your local CQ instance as described above. Then cd to `content/src/main/content/jcr_root` and run

    vlt --credentials admin:admin checkout -f ../META-INF/vault/filter.xml --force http://localhost:4502/crx

Once the working copy is created, you can use the normal ``vlt up`` and ``vlt ci`` commands.

Specifying CRX Host/Port
------------------------

The CRX host and port can be specified on the command line with:
mvn -Dcrx.host=otherhost -Dcrx.port=5502 <goals>


