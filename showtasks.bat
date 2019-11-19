call runcrud.bat
if "%ERRORLEVEL%" =="0" goto showtasks
echo.
echo RUNCRUD.BAT has problems - finishing work
goto fail

:showtasks
set browser=C:\"Program Files"\"Opera"\launcher.exe
set wait_time=2
start %browser% -new-tab "http://localhost:8080/crud/v1/task/getTasks"
goto end

:fail
echo.
echo Detected errors

:end
echo.
echo Work is finished.