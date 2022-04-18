<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Classe extends Model
{
    use HasFactory;
    protected $table='classes';
    protected $fillable = [
        "titre",
    ];

    public function departement(){
        return $this->belongsTo(Departement::class,'departement_id');
    }
}
