<?php

namespace Database\Factories;

use App\Models\Classe;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeClasseFactory extends Factory
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
            "idClasse" => Classe::get('id')->random(),
            "dateEmploi" => $this->faker->date(),
            "dateDeRemise" => $this->faker->date(),

        ];
    }
}
