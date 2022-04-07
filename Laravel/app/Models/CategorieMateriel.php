<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CategorieMateriel extends Model
{
    use HasFactory;
    protected $table='categorie_materiels';

    protected $fillable = [
        "titre",
    ];
    // relation this Category has many Materiels
    public function materiels(){
        return $this->hasMany(Materiel::class,'categorie_materiel_id');
    }
}
