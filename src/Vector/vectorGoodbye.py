import time
import anki_vector


def main():
    args = anki_vector.util.parse_command_args()
    with anki_vector.Robot(args.serial) as robot:
        robot.behavior.drive_off_charger()
        #print("List all animation names:")
        #anim_names = robot.anim.anim_list
        #for anim_name in anim_names:
        #    print(anim_name)
		
        # animation = 'anim_driving_upset_loop_02'
        # print("Playing animation by name: " + animation)
        # robot.anim.play_animation(animation)

        robot.say_text("Thank you for Listening.");
        robot.motors.set_lift_motor(7.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-7.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(7.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-7.0)
        robot.say_text("Good bye.");
        robot.motors.set_lift_motor(7.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-7.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(7.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-7.0)
		
        # Wait for 3 seconds (the head, lift and wheels will move while we wait)

        # Stop the motors, which unlocks the tracks
        robot.motors.set_wheel_motors(0, 0)
        robot.motors.set_lift_motor(0)
        robot.motors.set_head_motor(0)
        robot.behavior.drive_on_charger()


if __name__ == "__main__":
    main()
