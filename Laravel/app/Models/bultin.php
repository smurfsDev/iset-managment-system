<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class bultin extends Model
{
    use HasFactory;

    protected $fillable = [
        'student_id',
        'moyenne',
        'numero_bultin',
    ];

    public function student()
    {
        return $this->belongsTo('App\Models\User');
    }




}
