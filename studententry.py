#!/bin/python3



while True:
    cmd = input().split(" ")

    if cmd[0] == "Q" or cmd[0] == "Quit":
        exit();
    elif cmd[0] == "S" or cmd[0] == "Student":
        pass
    elif cmd[0] == "T" or cmd[0] == "Teacher":
        pass
    elif cmd[0] == "B" or cmd[0] == "Bus":
        pass
    elif cmd[0] == "G" or cmd[0] == "Grade":
        pass
    elif cmd[0] == "A" or cmd[0] == "Average":
        pass
    elif cmd[0] == "I" or cmd[0] == "Info":
        pass
    else:
        print("Invalid Command")


