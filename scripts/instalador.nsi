;---- INTERFAZ GRAFICA --------------
  !include "MUI2.nsh"
  !define MUI_ABORTWARNING
  !define NOMBREAPP "Agenda"
   Name "${NOMBREAPP}"
   Icon "agenda.ico"
   OutFile "Agenda.exe"
   DirText "Elija un directorio donde instalar la aplicación:"
   InstallDir "$PROGRAMFILES\${NOMBREAPP}"
   InstallDirRegKey HKCU "Software\Agenda" ""
   AutoCloseWindow false
   SetOverwrite on
   SetDatablockOptimize on

;---- INSTALADOR -------------------

  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES

;---- DESINSTALADOR ----------------

  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES
  !insertmacro MUI_LANGUAGE "Spanish"

;--------------------------------
Section "Agenda" agenda

  SetOutPath "$INSTDIR"
  SectionIn RO

  File "Agenda.jar"
  File "agenda.ico"

  SetShellVarContext all
  CreateDirectory "$SMPROGRAMS\${NOMBREAPP}"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk" "$INSTDIR\Agenda.jar" "" "$INSTDIR\agenda.ico"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk" "$INSTDIR\manual.pdf" "" ""
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk" "$INSTDIR\Uninstall.exe" "" ""

  CreateShortCut "$DESKTOP\${NOMBREAPP}.lnk" "$INSTDIR\${NOMBREAPP}.jar" "" "$INSTDIR\agenda.ico"
  SetShellVarContext all

  WriteRegStr HKCU "Software\Agenda" "" $INSTDIR
  WriteUninstaller "$INSTDIR\Uninstall.exe"

SectionEnd

;-----------------------------------------------------------------------
Section "Uninstall"
SetShellVarContext all

  delete "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk"

  delete "$DESKTOP\${NOMBREAPP}.lnk"
  rmDir "$SMPROGRAMS\${NOMBREAPP}"
 
  delete $INSTDIR\Agenda.jar
  delete $INSTDIR\manual.pdf
  delete $INSTDIR\agenda.ico
  delete $INSTDIR\Uninstall.exe
 
  rmDir $INSTDIR
  DeleteRegKey /ifempty HKCU "Agenda"
SectionEnd

;-----------------------------------------------------------------------
Section "Manual de usuario" manual

SetOutPath "$INSTDIR"
  File manual.pdf

SectionEnd

;-----------------------------------------------------------------------
;-----------------------------------------------------------------------
Section "Prerequisitos" prerequisitos

SectionIn RO

    ;DetailPrint "Comenzando la instalacion de java"     
    ;File "prerequisitos\java.exe"
    ;ExecWait "$INSTDIR\java.exe INSTALL_SILENT=1 AUTO_UPDATE=0 REBOOT=0 SPONSORS=0 REMOVEOUTOFDATEJRES=1 " 
    ;delete $INSTDIR\java.exe  
	
	;JAVA
	DetailPrint "Comenzando la instalacion de Java"     
    File "prerequisitos\java.exe"
    ExecWait "$INSTDIR\java.exe INSTALL_SILENT=1 AUTO_UPDATE=0 REBOOT=0 SPONSORS=0 REMOVEOUTOFDATEJRES=1"
	delete $INSTDIR\java.exe

	;MARIADB
    DetailPrint "Comenzando la instalacion de Mysql Server"
    File "prerequisitos\mariadb.msi"
    ExecWait '"msiexec" /i "$INSTDIR\mariadb.msi" SERVICENAME=MySQL DEFAULTUSER=1 PASSWORD=root PORT=3306 /qn INSTALLDIR="C:\MariaDB"'
    delete $INSTDIR\mariadb.msi

    ;DetailPrint "Comenzando la importacion de la base de datos"
	File "scriptAgenda.sql"
	ExecWait 'C:\MariaDB\bin\mysql --user=root --password=root -e "source $INSTDIR\scriptAgenda.sql"'
	delete $INSTDIR\scriptAgenda.sql
	
	;ExecWait "$INSTDIR\execute.sql"
    ;C:\Program Files\MariaDB 5.5\bin>mysql -u root -p
    ;C:\Program Files\MariaDB 5.5\bin>mysql.exe -root -root -hlocalhost -P3306 < C:\INSTEST\schema.sql
    ;nsExec::ExecToLog '"$0" /C "C:\mysql\bin\mysql.exe" -utheuser -pthepass -hlocalhost -P3306 < "$INSTDIR\schema.sql"'
    ;C:\Program Files\MariaDB 5.5\bin>mysqld ARRANCA EL SERVER
SectionEnd
;-------------------------------------------------------------------------
;-------------------------------------------------------------------------

  LangString DESC_Agenda ${LANG_SPANISH} "Archivos necesarios para la ejecución de la Agenda"
 ;LangString DESC_Prerequisitos ${LANG_SPANISH} "Archivos necesarios para que Agenda funcione correctamente"
  LangString DESC_Manual ${LANG_SPANISH} "Manual de usuario"

  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${Agenda} $(DESC_Agenda)
    ;!insertmacro MUI_DESCRIPTION_TEXT ${Prerequisitos} $(DESC_Prerequisitos)
    !insertmacro MUI_DESCRIPTION_TEXT ${Manual} $(DESC_Manual)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END
;--------------------------------