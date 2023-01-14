jpackage --name SmartCalc --input . --main-jar SmartCalc.jar --resource-dir package/macos --type pkg --app-version 3.0
jpackage --name SmartCalc --input . --main-jar SmartCalc.jar --resource-dir package/linux --type deb --app-version 3.0 --linux-shortcut --linux-menu-group "SmartCalc"
jpackage --name SmartCalc --input . --main-jar SmartCalc.jar --resource-dir package/macos --type exe --app-version 3.0 --win-dir-chooser --win-menu --win-menu-group "SmartCalc"

open SmartCalc-3.0.pkg
