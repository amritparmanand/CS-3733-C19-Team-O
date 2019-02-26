#!/usr/bin/env python3

# Copyright (c) 2018 Anki, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License in the file LICENSE.txt or at
#
#     https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""Drive Vector's wheels, lift and head motors directly

This is an example of how you can also have low-level control of Vector's motors
(wheels, lift and head) for fine-grained control and ease of controlling
multiple things at once.
"""

import time
import anki_vector


def main():
    args = anki_vector.util.parse_command_args()
    with anki_vector.Robot(args.serial) as robot:
        robot.behavior.drive_off_charger()
        print("List all animation names:")
        #anim_names = robot.anim.anim_list
        #for anim_name in anim_names:
        #    print(anim_name)
		
        animation = 'anim_referencing_giggle_01'
        print("Playing animation by name: " + animation)
        robot.anim.play_animation(animation)

        robot.say_text("Yay, An approval!");
        robot.motors.set_wheel_motors(150, -150)
        time.sleep(.1)
        robot.motors.set_lift_motor(2.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-2.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(2.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-2.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(2.0)
        time.sleep(.3)
        robot.motors.set_lift_motor(-2.0)
        time.sleep(.2)
		
        # Wait for 3 seconds (the head, lift and wheels will move while we wait)
     

        # Stop the motors, which unlocks the tracks
        robot.motors.set_wheel_motors(0, 0)
        robot.motors.set_lift_motor(0)
        robot.motors.set_head_motor(0)


if __name__ == "__main__":
    main()
