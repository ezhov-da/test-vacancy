echo off

set CLASS_DRIVER=org.h2.Driver
set URL=jdbc:h2:%~dp0test
set USER=test
set PASS=test
set COUNT_NUM=1000

echo �ࠩ��� ��� ������祭��: %CLASS_DRIVER%
echo URL ������祭��: %URL%
echo ���짮��⥫�: %USER%
echo ��஫�: %PASS%
echo ������⢮ ��� ����᪠: %COUNT_NUM%

cd /d %~dp0 
"%JAVA_HOME%"\bin\java -jar test-for-vacancy.jar %CLASS_DRIVER% %URL% %USER% %PASS% %COUNT_NUM%
pause