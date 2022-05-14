<?php

namespace Database\Factories;

use App\Models\DemandeEvent;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeAdhesionEventFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "idStudent" => User::get('id')->random(),
            "idEvent" => DemandeEvent::get('id')->random()
        ];
    }
}
