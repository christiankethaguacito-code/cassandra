Set WshShell = CreateObject("WScript.Shell")
Set fso = CreateObject("Scripting.FileSystemObject")

' Get the directory where this script is located
scriptDir = fso.GetParentFolderName(WScript.ScriptFullName)
jarPath = scriptDir & "\target\financial-management-1.0.0-desktop.jar"

' Launch the JAR file with javaw (no console window)
WshShell.Run "javaw -jar """ & jarPath & """", 0, False

Set WshShell = Nothing
Set fso = Nothing
