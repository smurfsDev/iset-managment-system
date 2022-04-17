<?php

namespace Database\Factories;

use App\Models\CategorieMateriel;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class MaterielFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "titre" => $this->faker->word(),
            "quantité" => $this->faker->randomDigit(),
            "description" => $this->faker->sentence(),
            "idResponsable" => User::get('id')->random(),
            "idCategorie" => CategorieMateriel::get('id')->random(),
        ];
    }
}
