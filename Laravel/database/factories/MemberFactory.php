<?php

namespace Database\Factories;

use App\Models\club;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class MemberFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "user_id" => User::get("id")->random(),
            "club_id" => club::get("id")->random(),
        ];
    }
}
