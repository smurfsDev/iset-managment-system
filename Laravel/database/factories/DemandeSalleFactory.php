<?php

namespace Database\Factories;

use App\Models\Salle;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeSalleFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "idResponsable" => User::get('id')->random(),
            "idSalle" => Salle::get('id')->random(),
            "dateEmploi" => $this->faker->date(),
            "dateDeRemise" => $this->faker->date(),
            "status"  =>$this->faker->numberBetween(1,3),
            "reponse" =>$this->faker->sentence(),

        ];
    }
}
