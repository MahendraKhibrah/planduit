share-firebase:
	firebase appdistribution:distribute app/build/outputs/apk/debug/app-debug.apk --app 1:461892192994:android:f142e7cd9637904bcc6cbb --release-notes-file "buildSrc/src/main/java/releaseNotes.txt" --groups "developer-team"

share-firebase-release:
	firebase appdistribution:distribute app/release/app-release.apk --app 1:461892192994:android:30c7e25c9dc74679cc6cbb --release-notes-file "buildSrc/src/main/java/releaseNotes.txt" --groups "developer-team"